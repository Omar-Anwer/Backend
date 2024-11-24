import express, { Request, Response, Application, NextFunction } from 'express';
import cors from 'cors';
import userRoutes from './routes/api/v1/userRoutes';

const allowedOrigins = process.env.CORS_ORIGIN || '';

const allowedOriginsArray = allowedOrigins
    .split(',')
    .map((item) => item.trim());

const corsOptions = {
    origin: allowedOriginsArray, // whitelist foreign domains to allow
    optionSuccessStatus: 200, //Some legacy browsers
    // allowedHeaders: [
    //     'access-control-allow-origin',
    //     'authorization',
    //     'Pragma',
    //     'contact',
    //   ],
    //   exposeHeaders: []
};

const app: Application = express();

const env = process.env.NODE_ENV;
const port = process.env.PORT || 3000;
const address: string = `http://localhost:${port}`;

// Middleware
app.use(express.json());
app.use(express.static('./public'));
app.use(cors(corsOptions));

// Error handling
//app.use(errorMiddleware);

// Routes
app.use('/api/users', userRoutes);

app.get('/', (_req: Request, res: Response) => {
    res.send('Welcome to the Products Store API!');
});



app.get(
    '/test-cors',
    cors(corsOptions),
    function (_req: Request, res: Response, next: NextFunction) {
        res.json({ msg: `This is CORS-enabled with a middleware` });
    },
);

app.all('*', (_req: Request, res: Response) => {
    res.status(404).send('Oops!');
});

const start = async () => {
    app.listen(port, () => {
        console.log(`Server is listening at ${address}....`);
    });
};

start();

export default app;
