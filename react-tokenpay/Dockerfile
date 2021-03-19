FROM node:11.15.0-alpine

# set working directory
WORKDIR /app

# add `/app/node_modules/.bin` to $PATH
ENV PATH /app/node_modules/.bin:$PATH

# install app dependencies
COPY package.json ./


RUN apk add python make g++

RUN npm install

# add app
COPY . .

EXPOSE 3000

# start app
CMD ["npm", "start"]
