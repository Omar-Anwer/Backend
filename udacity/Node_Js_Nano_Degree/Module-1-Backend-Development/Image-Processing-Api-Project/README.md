# Image Processing API

## Project Description

This project is an API designed to:

Provides a properly scaled versions of images to reduce page load size. This eliminates the need to manually resize and upload multiple copies of the same image. 
The project is set up using Node.js, TypeScript, and industry best practices to ensure it is scalable and maintainable. It includes unit testing, linting, and formatting to ensure that the codebase is easy to read, debug, and maintain for future enterprise-level expansions.

---
## Getting Started ##

### Installing dependencies ###

After cloning the repo, all the project dependencies can be installed using npm:
```
npm install
```

### Running the server ###

To execute the application on local development environment use the following command in terminal:

```
npm run dev
```

the app will then be available on port 3000 by default, but that can be changed by editing the port constant value in the app.ts file.

### Scripts ###

The following actions can be executed through npm scripts:

#### Transpiling typescript to javascript ####

```
npm run build
```

The transpiled code will be available in the `build` folder.

#### Testing ####

A jasmine testing suite can be used to validate the endpoint as well as the imageTransform functionality.

```
npm run test
```

#### Formatting ####

The code can be automatically formatted using prettier. The formatting options can be customised by editin the `.prettierrc`file.

```
npm run prettier
```

#### Linting ####

The code can ba automatically linted using ESlint. Note that ESlint will also use prettier to test for incorrect formatting. Rules, plugins and extensions for ESlint can be modified through the `.eslintrc.json` file.

```
npm run lint
```
---

## Endpoint: /api/v1/images/resize


### Query Parameters

| Parameter  | Type   | Required | Description                                                                 |
|------------|--------|----------|-----------------------------------------------------------------------------|
| `filename` | string | Yes      | The name of the jpg image file (without extension) to resize. Must be available in the images folder. |
| `width`    | number | Yes      | The desired width (in pixels) of the output image. Must be a positive integer. |
| `height`   | number | Yes      | The desired height (in pixels) of the output image. Must be a positive integer. |

### Example Request

```bash
curl "http://localhost:3000/api/v1/images/resize?filename=fjord&width=300&height=300"
```

### Example URL

```bash
http://localhost:3000/api/v1/images/resize?filename=fjord&width=300&height=300
```

#### Method: `GET`

#### Response:

- Status: `200 OK`
- Body:
    Resized Image