#!/usr/bin/env sh
set -eu

gateway_url="${GATEWAY_URL:-http://localhost:8000}"
requests="${REQUESTS:-100}"

i=1
while [ "$i" -le "$requests" ]; do
  curl --fail --silent --output /dev/null \
    --header 'Content-Type: application/json' \
    --data "{\"customerId\":\"load-${i}\",\"amount\":${i}.00}" \
    "${gateway_url}/orders"
  i=$((i + 1))
done

printf 'Published %s orders to %s\n' "$requests" "$gateway_url"