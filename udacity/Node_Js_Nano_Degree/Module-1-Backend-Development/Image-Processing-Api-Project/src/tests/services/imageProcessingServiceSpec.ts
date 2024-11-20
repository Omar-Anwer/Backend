import path from 'path';
import { resizeImage } from '../../services/imageProcessingService';

describe('Test image processing feature.', () => {
    it('should resize the image successfully without throwing for valid parameters.', async () => {
        const testFilePath = path.join(
            __dirname,
            '../../../public/images/full',
            `fjord.jpg`,
        );
        const testThumbPath = path.join(
            __dirname,
            '../../../public/images/thumb',
        );

        const testWidth = 200;
        const testHeight = 200;

        expect(async () => {
            await resizeImage(
                testFilePath,
                testWidth,
                testHeight,
                testThumbPath,
            );
        }).not.toThrow();
    });
});
