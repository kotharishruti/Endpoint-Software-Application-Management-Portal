#!/bin/bash

cd /home/esamp/chef-repo

echo "client list"
echo $1 $2 $3 $4
knife client list

echo "bootstrapping"

knife bootstrap $1 --ssh-user root --ssh-password $2 --sudo --use-sudo-password --node-name $3 

echo "DONE!"

echo $4
knife node run_list add $3 'recipe['$4']'

echo "recipe added successfully!"

knife ssh $1 'chef-client' --manual-list --ssh-user root --ssh-password $2

echo "Running chef-client!"

knife node run_list remove $3 'recipe['$4']'


