#!/bin/bash

echo "dependency s/w"

cd /home/esamp/chef-repo/

knife cookbook site install $1 #name of software

knife cookbook upload $1 --include-dependencies

echo "uploaded!"
