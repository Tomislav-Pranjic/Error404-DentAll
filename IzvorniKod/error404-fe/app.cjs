const express = require('express');
const {createProxyMiddleware } = require('http-proxy-middleware');
require("dotenv").config();
const path = require('path');

const app = express();

const { PORT } = process.env;
const { API_URL } = process.env;
const { HOST } = process.env;

app.use("/api", createProxyMiddleware({ target: API_URL, changeOrigin: true }));

app.use(express.static(path.join(__dirname, 'dist')));

app.listen(PORT, HOST, () => {
    console.log(`Starting Proxy at ${HOST}:${PORT}`);
});

app.get('*', async (req, res) => {
    res.sendFile(path.join(__dirname, 'dist', 'index.html'));
});