import express from 'express';
import imageRoute from './api/imageRoutes';

const routes = express();

routes.use('/images', imageRoute);

export default routes;
