import supertest from 'supertest';
import app from '../app';
const request = supertest(app);
describe('name of the test suite', () => {
    //beforeAll
    //afterAll
    //beforeEach -- (setupFn)
    //afterEach -- (teardownFn)
    it('should return 404 for an invalid URL.', async () => {
        const res = await request.get('/invalid');
        expect(res.status).toBe(404);
    });

    it('should return 200 for a valid endpoint.', async () => {
        const res = await request.get('/api/v1/images');
        expect(res.status).toBe(200);
    });
});
