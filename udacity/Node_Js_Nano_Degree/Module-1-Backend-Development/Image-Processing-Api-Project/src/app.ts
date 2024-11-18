import express, { Request, Response } from 'express';

import routes from './routes/index';

const app = express();
const port = 3000;

app.use('/api/v1', routes);

app.use(express.static('./public'));

app.get('/', (req: Request, res: Response): void => {
    // res.sendFile(path.resolve(__dirname, './index.html'));
    res.send('main api route');
});

app.all('*', (req: Request, res: Response): void => {
    res.status(404).send('<h1>Page Not Found!</h1>');
});

app.listen(port, () => {
    console.log(`server started at localhost:${port}`);
});
