# Build stage
FROM node:18-alpine as builder
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

# Production stage (with SSR support)
FROM node:18-alpine
WORKDIR /app
COPY --from=builder /app/dist/creditos-front /app
COPY --from=builder /app/node_modules /app/node_modules
EXPOSE 4000
ENV NODE_ENV=production
CMD ["node", "server/server.mjs"]