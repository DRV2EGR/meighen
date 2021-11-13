#### Stage 1: Build the react application
FROM node:12.4.0-alpine as build

WORKDIR /app

COPY package.json package-lock.json ./
RUN npm install
RUN npm i es-abstract

COPY . ./
# VOLUME /public

ARG REACT_APP_API_BASE_URL
ENV REACT_APP_API_BASE_URL=${REACT_APP_API_BASE_URL}

RUN npm run build

#### Stage 2: Serve the React application from Nginx 
FROM nginx:1.17.0-alpine

COPY --from=build /app/build /var/www
# Copy my custom config for nginx
COPY nginx.conf /etc/nginx/nginx.conf

EXPOSE 80
ENTRYPOINT ["nginx","-g","daemon off;"]