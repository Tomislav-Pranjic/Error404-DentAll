const express = require('express');
const {createProxyMiddleware } = require('http-proxy-middleware');
require("dotenv").config();
const path = require('path');

const app = express();

const { PORT } = process.env;
const { API_URL } = process.env;

app.use(express.static(path.join(__dirname, 'dist')));

app.use("/api", createProxyMiddleware({ target: API_URL, changeOrigin: true }));

app.listen(PORT || 5173, () => {
    console.log(`Starting server at localhost:${PORT}`);
});
app.use('*', async (req, res) => {
    res.sendFile(path.join(__dirname, 'dist', 'index.html'));
});