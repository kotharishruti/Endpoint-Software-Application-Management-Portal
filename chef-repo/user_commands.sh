#!/bin/bash

echo "User commands"
echo $3 $1 $2 $4

cd /home/esamp/chef-repo

echo ''>cmd.rb
echo ''>/home/esamp/chef-repo/cookbooks/usr_cmd/recipes/default.rb

cat template_cmd.rb>cmd.rb

grep -rl path cmd.rb | xargs sed -i "s#path#$3 $4 $5 $6#g"

#knife ssh $1 ''$3'' --manual-list --ssh-user root --ssh-password $2

#knife cookbook create usr_cmd

#knife cookbook create $1

cd /home/esamp/chef-repo/cookbooks/usr_cmd/recipes

cat /home/esamp/chef-repo/cmd.rb>>default.rb

echo "Uploading cookbook"

knife cookbook upload usr_cmd

echo "Success!"

knife node run_list add N1.1.5.22 'recipe[usr_cmd]'

echo "recipe added successfully!"

knife ssh $1 'chef-client' --manual-list --ssh-user root --ssh-password $2

echo "Running chef-client!"



echo "Running commands!"

knife node run_list remove N1.1.5.22 'recipe[usr_cmd]'

#knife cookbook delete usr_cmd
