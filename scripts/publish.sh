#!/usr/bin/env sh
set -eu

gateway_url="${GATEWAY_URL:-http://localhost:8000}"

curl --fail-with-body --silent --show-error \
  --header 'Content-Type: application/json' \
  --data '{"customerId":"demo-customer","amount":42.50}' \
  "${gateway_url}/orders"
printf '\n'