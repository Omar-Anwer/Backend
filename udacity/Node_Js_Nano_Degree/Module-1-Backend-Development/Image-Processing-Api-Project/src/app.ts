import express, { Request, Response } from 'express';

import routes from './routes/index';

const app = express();
const port = 3000;

app.use('/api/v1', routes);

app.use(express.static('./public'));

app.all('*', (req: Request, res: Response): void => {
    res.status(404).send('<h1>Page Not Found!</h1>');
});

app.listen(port, () => {
    console.log(`Server listening at http://localhost:${port}`);
});
