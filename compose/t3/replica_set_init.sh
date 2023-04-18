#!/bin/bash

set -e
set -u
# set -x	# Uncomment for debugging


# The replica set configuration document
#
# mongodb1: Primary, since we initiate the replica set on monog0
# mongodb2: Secondary
# mongodb3: Arbiter, since we set the 'arbiterOnly' option to true
_config=\
'
{
	"_id": "rs0",
	"members": [
		{ "_id": 0, "host": "mongodb1" },
		{ "_id": 1, "host": "mongodb2" },
		{ "_id": 2, "host": "mongodb3", arbiterOnly: true },
	]
}
'

sleep 5;


if [[ -n "${DB_USERNAME:-}" && -n "${DB_PASSWORD:-}" ]]; then
	mongosh --quiet \
	--host mongodb1 \
	-u $DB_USERNAME -p $DB_PASSWORD \
	--authenticationDatabase admin \
	<<-EOF
		rs.initiate($_config);
	EOF
else
	mongosh --quiet \
	--host mongodb1 \
	<<-EOF
		rs.initiate($_config);
	EOF
fi

exec "$@"