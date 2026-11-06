FROM node:20-alpine AS builder
WORKDIR /app

RUN npm install -g pnpm

COPY package.json ./

RUN pnpm install --config.node-linker=hoisted --dangerously-allow-all-builds

COPY . .
RUN pnpm run build --configuration=production

FROM nginx:alpine
RUN rm -rf /usr/share/nginx/html/*
COPY --from=builder /app/dist/docutrace-frontend/browser /usr/share/nginx/html

RUN chown -R nginx:nginx /usr/share/nginx/html && \
    chmod -R 755 /usr/share/nginx/html && \
    chown -R nginx:nginx /var/cache/nginx && \
    chown -R nginx:nginx /var/log/nginx && \
    chown -R nginx:nginx /etc/nginx/conf.d

RUN touch /var/run/nginx.pid && \
    chown -R nginx:nginx /var/run/nginx.pid

RUN sed -i 's/listen       80;/listen       8080;/' /etc/nginx/conf.d/default.conf

USER nginx
EXPOSE 8080
ENTRYPOINT ["nginx", "-g", "daemon off;"]
