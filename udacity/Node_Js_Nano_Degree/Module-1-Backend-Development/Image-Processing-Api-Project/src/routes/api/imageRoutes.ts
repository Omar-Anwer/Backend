import express, { Request, Response } from 'express';

const imageRoute = express.Router();

imageRoute.get('/', (req: Request, res: Response): void => {
    console.log(req.query);
    res.status(200).send('image route');
});

export default imageRoute;
