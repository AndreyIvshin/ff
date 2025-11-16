# Master Service

___

### Useful Commands

```bash
docker run \
  --name ms-pg \
  -e POSTGRES_DB=ms \
  -e POSTGRES_USER=ms \
  -e POSTGRES_PASSWORD=ms \
  -p 5432:5432 \
  -d postgres:17-alpine
docker exec -it ms-pg bash
psql -h localhost -p 5432 -U ms -d ms

gradle clean ms-app:test --info
```
