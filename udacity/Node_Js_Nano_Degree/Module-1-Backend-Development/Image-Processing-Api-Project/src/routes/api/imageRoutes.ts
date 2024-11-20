import express, { Request, Response } from 'express';
import path from 'node:path';
import fs from 'node:fs';
import { resizeImage } from '../../services/imageProcessingService';

const imageRoute = express.Router();

const imagesDir = path.join(__dirname, '../../../public/images/');

imageRoute.get('/resize', async (req: Request, res: Response) => {
    const { filename, width, height } = req.query;

    // Validate query parameters
    if (!filename || !width || !height) {
        const errorMsg =
            'Missing required query parameters: filename, width, and height';
        console.error(errorMsg);
        res.status(400).json({ error: errorMsg });
        return;
    }

    // Validate filename
    const imagePath = path.join(imagesDir, 'full', `${filename}.jpg`);

    if (!fs.existsSync(imagePath)) {
        const errorMsg = `Image '${filename}' not found in ${imagePath}`;
        console.error(errorMsg);
        res.status(404).json({ error: errorMsg });
        return;
    }

    // Validate dimensions
    const widthNum = Number(width);
    const heightNum = Number(height);

    if (
        !Number.isInteger(widthNum) ||
        !Number.isInteger(heightNum) ||
        widthNum <= 0 ||
        heightNum <= 0
    ) {
        const errorMsg =
            'Invalid width or height, width and height must be non-zero positive integers';
        console.error(errorMsg);
        res.status(400).json({ error: errorMsg });
        return;
    }

    const widthInt = parseInt(width as string, 10);
    const heightInt = parseInt(height as string, 10);

    const resizedImagePath = path.join(
        imagesDir,
        'thumb',
        `${filename}-${widthInt}x${heightInt}.jpg`,
    );

    // Check if resized image already exists
    if (fs.existsSync(resizedImagePath)) {
        console.log('resized image already exists');
        res.sendFile(resizedImagePath);
        return;
    }

    try {
        // Resize the image
        await resizeImage(imagePath, widthInt, heightInt, resizedImagePath);
        // Send the resized image
        res.sendFile(resizedImagePath);
        return;
    } catch (error) {
        const errorMsg = 'Error processing image: ';
        console.error(errorMsg, error);
        res.status(500).json({
            error: errorMsg,
        });
        return;
    }
});

export default imageRoute;
