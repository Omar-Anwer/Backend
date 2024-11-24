// @ts-ignore
import client from '../database/database.ts';
// import bcrypt from 'bcrypt';

export type User = {
    id?: number;
    username: string;
    password: string;
    firstName: string;
    lastName: string;
};

export default class Users {
    static async findAll(): Promise<User[]> {
        try {
            // @ts-ignore
            const conn = await client.connect();
            const sql = 'SELECT * FROM USERS';
            const result = await conn.query(sql);
            conn.release();
            const users = result.rows;
            return users;
        } catch (err) {
            throw new Error(`Could not get users. Error: ${err}`);
        }
    }

    static async findById(id: number): Promise<User> {
        try {
            // @ts-ignore
            const conn = await client.connect();
            const sql = 'SELECT * FROM users WHERE id=($1)';
            const result = await conn.query(sql, [id]);
            conn.release();
            if (result.rows.length === 0) {
                throw new Error(`User with id ${id} does not exist.`);
            }
            const user = result.rows[0];
            return user;
        } catch (err) {
            throw new Error(`Could not find user with id ${id}. Error: ${err}`);
        }
    }

    static async create(u: User): Promise<User> {
        try {
            // @ts-ignore
            const conn = await client.connect();
            const sql = `
                INSERT INTO users (username, password, firstName, lastName)
                VALUES ($1, $2, $3, $4)
                RETURNING id, username, firstName, lastName
            `;
            // const hash = bcrypt.hashSync(
            //     u.password + pepper,
            //     parseInt(saltRounds),
            // );
            const result = await conn.query(sql, [
                u.username,
                u.password,
                u.firstName,
                u.lastName,
            ]);
            conn.release();
            const user = result.rows[0];
            return user;
        } catch (err) {
            throw new Error(`Could not create user. Error: ${err}`);
        }
    }

    static async delete(id: number): Promise<void> {
        try {
            // @ts-ignore
            const conn = await client.connect();
            const sql = 'DELETE FROM Users WHERE id=($1) RETURNING *';
            const result = await conn.query(sql, [id]);
            conn.release();
            if (result.rows.length === 0) {
                throw new Error(`User with id ${id} does not exist.`);
            }
            return result.rows[0];
        } catch (err) {
            throw new Error(
                `Could not delete user with id ${id}. Error: ${err}`,
            );
        }
    }

    static async update(id: number, u: Partial<User>): Promise<User> {
        try {
            const conn = await client.connect();
            const fields: string[] = [];
            const values: any[] = [];
            let counter = 1;

            for (const [key, value] of Object.entries(u)) {
                fields.push(`${key} = $${counter}`);
                values.push(value);
                counter++;
            }

            const sql = `
                UPDATE users
                SET ${fields.join(', ')}
                WHERE id = $${counter}
                RETURNING id, username, firstName, lastName
            `;
            values.push(id);

            const result = await conn.query(sql, values);
            conn.release();
            if (result.rows.length === 0) {
                throw new Error(`User with id ${id} does not exist.`);
            }
            return result.rows[0];
        } catch (err) {
            throw new Error(
                `Could not update user with id ${id}. Error: ${err}`,
            );
        }
    }

    //     async authenticate(
    //         username: string,
    //         password: string,
    //     ): Promise<User | null> {
    //         const conn = await client.connect();
    //         const sql = 'SELECT password_digest FROM users WHERE username=($1)';

    //         const result = await conn.query(sql, [username]);

    //         console.log(password + pepper);

    //         if (result.rows.length) {
    //             const user = result.rows[0];

    //             console.log(user);

    //             if (bcrypt.compareSync(password + pepper, user.password_digest)) {
    //                 return user;
    //             }
    //         }

    //         return null;
    //     }
}
