# syntax=docker/dockerfile:1
# Dockerfile
FROM node:11.13.0-alpine

ENV APP=/app

# create destination directory
RUN mkdir $APP
WORKDIR $APP

# update and install dependency
RUN apk update && apk upgrade

COPY . $APP

RUN npm install
RUN npm run generate

EXPOSE 3000

ENV NUXT_HOST=0.0.0.0
ENV NUXT_PORT=3000

CMD [ "npm", "start" ]
