FROM kong:3.9

COPY kong.yaml /usr/local/kong/declarative/kong.yaml

ENV KONG_DATABASE=off \
    KONG_DECLARATIVE_CONFIG=/usr/local/kong/declarative/kong.yaml \
    KONG_PROXY_ACCESS_LOG=/dev/stdout \
    KONG_ADMIN_ACCESS_LOG=/dev/stdout \
    KONG_PROXY_ERROR_LOG=/dev/stderr \
    KONG_ADMIN_ERROR_LOG=/dev/stderr