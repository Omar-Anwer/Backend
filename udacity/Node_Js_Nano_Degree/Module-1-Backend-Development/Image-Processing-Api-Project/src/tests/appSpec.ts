import supertest from 'supertest';
import app from '../app';
import sharp from 'sharp';
import fs from 'fs';
import path from 'path';

// Directories and request setup
const THUMB_DIR = './public/images/thumb';
const request = supertest(app);

// Configuration for multiple endpoints
const ENDPOINTS = {
    resizeImage: '/api/v1/images/resize',
    invalid: '/invalid',
};

// Helper to build URL with query parameters
const createURL = (
    endpoint: string,
    params: Record<string, string | number | undefined> = {},
): string => {
    const queryParams = new URLSearchParams();
    for (const [key, value] of Object.entries(params)) {
        if (value !== undefined) {
            queryParams.append(key, value.toString());
        }
    }
    return `${endpoint}?${queryParams.toString()}`;
};

describe('API Endpoint Tests', () => {
    beforeAll(() => {
        // Ensure the thumbnail directory exists for tests
        if (!fs.existsSync(THUMB_DIR)) {
            fs.mkdirSync(THUMB_DIR, { recursive: true });
        }
    });

    afterAll(() => {
        // Cleanup files inside the thumb directory, but keep the directory
        const files = fs.readdirSync(THUMB_DIR);
        files.forEach((file) => {
            fs.unlinkSync(path.join(THUMB_DIR, file));
        });
    });

    describe('General Endpoint Tests', () => {
        it('should return 404 for an invalid URL.', async () => {
            const res = await request.get(ENDPOINTS.invalid);
            expect(res.status).toBe(404);
        });
    });

    describe('Image Resize Endpoint Tests', () => {
        it('should return 404 for a non-existent image.', async () => {
            const res = await request.get(
                createURL(ENDPOINTS.resizeImage, {
                    filename: 'nonexistent',
                    width: 100,
                    height: 100,
                }),
            );
            expect(res.status).toBe(404);
        });

        it('should return 400 for missing query parameters.', async () => {
            const res = await request.get(ENDPOINTS.resizeImage);
            expect(res.status).toBe(400);
        });

        it('should return 400 if height is invalid: NaN.', async () => {
            const res = await request.get(
                createURL(ENDPOINTS.resizeImage, {
                    filename: 'fjord',
                    width: 200,
                    height: 'a',
                }),
            );
            expect(res.status).toBe(400);
        });

        //if width or height is less than or equal to zero.'

        it('should return 400 if height is invalid: zero.', async () => {
            const res = await request.get(
                createURL(ENDPOINTS.resizeImage, {
                    filename: 'fjord',
                    width: 200,
                    height: 0,
                }),
            );
            expect(res.status).toBe(400);
        });

        it('should return 400 if height is invalid: negative', async () => {
            const res = await request.get(
                createURL(ENDPOINTS.resizeImage, {
                    filename: 'fjord',
                    width: 200,
                    height: -1,
                }),
            );
            expect(res.status).toBe(400);
        });

        it('should return 400 if height is invalid: float', async () => {
            const res = await request.get(
                createURL(ENDPOINTS.resizeImage, {
                    filename: 'fjord',
                    width: 100,
                    height: 190.9,
                }),
            );
            expect(res.status).toBe(400);
        });

        it('should return 200 and the resized image for valid query parameters.', async () => {
            const testParams = { filename: 'fjord', width: 200, height: 100 };

            const res = await request.get(
                createURL(ENDPOINTS.resizeImage, testParams),
            );

            expect(res.status).toBe(200);
            expect(res.headers['content-type']).toContain('image/jpeg');

            // Check the image dimensions
            const buffer = res.body as Buffer;
            const metadata = await sharp(buffer).metadata();
            expect(metadata.width).toBe(testParams.width);
            expect(metadata.height).toBe(testParams.height);
        });
    });
});
