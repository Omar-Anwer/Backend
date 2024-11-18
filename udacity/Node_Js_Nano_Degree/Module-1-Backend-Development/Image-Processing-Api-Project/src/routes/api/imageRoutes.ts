import express, { Request, Response } from 'express';
import path from 'node:path';
import fs from 'node:fs';
import sharp from 'sharp';

const imageRoute = express.Router();

const imagesDir = path.join(__dirname, '../../../public/images/');

imageRoute.get('/', async (req: Request, res: Response) => {
    const { filename, width, height } = req.query;

    // Validate query parameters
    if (!filename || !width || !height) {
        res.status(400).json({
            error: 'Missing required query parameters: filename, width, and height',
        });
        return;
    }

    // Validate filename
    const imagePath = path.join(imagesDir, 'full/', `${filename}.jpg`);

    if (!fs.existsSync(imagePath)) {
        res.status(404).json({
            error: `Image '${filename}' not found in ${imagePath}`,
        });
        return;
    }

    // Validate dimensions
    const widthInt = parseInt(width as string, 10);
    const heightInt = parseInt(height as string, 10);

    if (isNaN(widthInt) || isNaN(heightInt)) {
        res.status(400).json({ error: 'Invalid width, and height' });
        return;
    }

    if (widthInt <= 0 || heightInt <= 0) {
        res.status(400).json({
            error: 'Width and height must be positive integers',
        });
        return;
    }
    const resizedImagePath = path.join(
        imagesDir,
        'thumb/',
        `${filename}-${widthInt}x${heightInt}.jpg`,
    );
    console.log('resizedImagePath:', resizedImagePath);

    try {
        // Resize the image
        await sharp(imagePath)
            .resize(widthInt, heightInt)
            .toFile(resizedImagePath);
        // Send the resized image
        res.sendFile(resizedImagePath);
        return;
    } catch (error) {
        console.error('Error processing image:', error);
        res.status(500).json({
            error: 'Failed to process image',
        });
        return;
    }
});

export default imageRoute;
