# syntax=docker/dockerfile:1
# Dockerfile
FROM node:11.13.0-alpine

ENV APP=/app

# create destination directory
RUN mkdir $APP
WORKDIR $APP

# update and install dependency
RUN apk update && apk upgrade

# copy the app, note .dockerignore
COPY . $APP
RUN npm install
RUN npm run build

EXPOSE 3000

ENV NUXT_HOST=0.0.0.0
ENV NUXT_PORT=3000

CMD [ "npm", "start" ]
