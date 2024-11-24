import dotenv from 'dotenv';
import { Pool } from 'pg';

dotenv.config();

const client = new Pool({
    host: process.env.DB_HOST,
    port: process.env.DB_PORT ? parseInt(process.env.DB_PORT, 10) : undefined,
    user: process.env.DB_USER,
    password: process.env.DB_PASSWORD,
    // database: process.env.DB_NAME,
    database:
        process.env.NODE_ENV === 'test'
            ? process.env.DB_TEST_NAME
            : process.env.DB_NAME,
    //max: 20,
    // Set the maximum number of clients in the pool
    //idleTimeoutMillis: 30000,
    // Close idle clients after 30 seconds
    //connectionTimeoutMillis: 2000,
    // Close new clients after 2 seconds of inactivity
});

client.on('error', (err) => {
    console.error('Unexpected database error:', err);
    process.exit(-1);
});

export default client;
