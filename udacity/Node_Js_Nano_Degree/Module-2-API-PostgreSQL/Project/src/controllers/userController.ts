import { Request, Response, NextFunction } from 'express';
import Users, { User } from '../models/user';

export const getAllUsers = async (
    _req: Request,
    res: Response,
    next: NextFunction,
) => {
    try {
        const users = await Users.findAll();
        res.json(users);
    } catch (err) {
        next(err);
    }
};

export const getUserById = async (
    req: Request,
    res: Response,
    next: NextFunction,
) => {
    try {
        const userId = parseInt(req.params.id);
        const user = await Users.findById(userId);
        if (!user) {
            res.status(404).json({
                error: `User with id ${userId} not found.`,
            });
            return;
        }
        res.status(200).json(user);
    } catch (err) {
        next(err);
    }
};

export const createUser = async (
    req: Request,
    res: Response,
    next: NextFunction,
) => {
    try {
        const user: User = {
            username: req.params.username,
            password: req.params.password,
            firstName: req.params.firstName,
            lastName: req.params.lastName,
        };

        const createdUser = await Users.create(user);
        res.status(201).json(createdUser);
    } catch (err) {
        next(err);
    }
};

export const updateUser = async (
    req: Request,
    res: Response,
    next: NextFunction,
) => {
    try {
        const userId = parseInt(req.params.id);
        const updates = req.body;
        const updatedUser = await Users.update(userId, updates);
        res.status(200).json(updatedUser);
    } catch (err) {
        next(err);
    }
};

export const deleteUser = async (
    req: Request,
    res: Response,
    next: NextFunction,
) => {
    try {
        const userId = parseInt(req.params.id);
        const deletedUser = await Users.delete(userId);
        res.status(200).json(deletedUser);
    } catch (err) {
        next(err);
    }
};
