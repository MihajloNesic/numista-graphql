#!/bin/bash

docker rmi mihajlonesic/numista-graphql:latest || true
docker build -t mihajlonesic/numista-graphql:latest .
docker run -d --name numista-graphql -p 8089:8089 mihajlonesic/numista-graphql:latest