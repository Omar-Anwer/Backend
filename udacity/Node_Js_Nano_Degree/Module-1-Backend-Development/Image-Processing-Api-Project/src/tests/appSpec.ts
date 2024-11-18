import supertest from 'supertest';
import app from '../app';
import sharp from 'sharp';
import fs from 'fs';
import path from 'path';

const THUMB_DIR = './public/images/thumb';
const request = supertest(app);

describe('Test GET /api/v1/images endpoint', () => {
    beforeAll(() => {
        // Ensure the thumbnail directory exists for tests
        if (!fs.existsSync(THUMB_DIR)) {
            fs.mkdirSync(THUMB_DIR, { recursive: true });
        }
    });

    afterAll(() => {
        // Cleanup only the files inside the thumb directory
        const files = fs.readdirSync(THUMB_DIR);
        files.forEach((file) => {
            fs.unlinkSync(path.join(THUMB_DIR, file));
        });
    });

    it('should get a 404 for any invalid URL', async () => {
        const res = await request.get('/invalid');
        expect(res.status).toBe(404);
    });

    it('should return 404 for a non-existent image', async () => {
        const res = await request.get(
            '/api/v1/images?filename=nonexistent&width=100&height=100',
        );
        expect(res.status).toBe(404);
        // expect(res.body.error).toContain("Image 'nonexistent' not found");
    });

    it('should return 400 for any missing query parameters', async () => {
        const res = await request.get('/api/v1/images');
        expect(res.status).toBe(400);
        // expect(res.body.error).toBe(
        //     'Missing required query parameters: filename, width, and height',
        // );
    });

    it('should return 400 if width is invalid', async () => {
        const res = await request.get(
            '/api/v1/images?filename=fjord&width=abc&height=200',
        );
        expect(res.status).toBe(400);
    });

    it('should return 400 if height is invalid', async () => {
        const res = await request.get(
            '/api/v1/images?filename=fjord&width=200&height=abc',
        );
        expect(res.status).toBe(400);
    });

    it('should return 400 if width or height is less than or equal to zero', async () => {
        const res = await request.get(
            '/api/v1/images?filename=fjord&width=0&height=100',
        );
        expect(res.status).toBe(400);
        // expect(res.body.error).toBe('Width and height must be positive integers');
    });

    it('should return 200 and the resized image for valid query parameters', async () => {
        const filename = 'fjord';
        const width = 200;
        const height = 100;

        const res = await request.get(
            `/api/v1/images?filename=${filename}&width=${width}&height=${height}`,
        );

        expect(res.status).toBe(200);
        expect(res.headers['content-type']).toContain('image/jpeg');

        // Check the image dimensions
        const buffer = res.body as Buffer;
        const metadata = await sharp(buffer).metadata();
        expect(metadata.width).toBe(width);
        expect(metadata.height).toBe(height);
    });
});
