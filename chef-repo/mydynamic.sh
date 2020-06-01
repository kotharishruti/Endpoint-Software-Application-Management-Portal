#!/bin/bash

echo "Creating recipe"

cd /home/esamp/chef-repo

echo ''>default.rb

cat template.rb>default.rb

grep -rl default default.rb | xargs sed -i "s/default/$1/g"

echo "Creating cookbook"

cd /home/esamp/chef-repo/

knife cookbook create $1

cd /home/esamp/chef-repo/cookbooks/$1/recipes

cat /home/esamp/chef-repo/default.rb>>default.rb

echo "Uploading cookbook"

knife cookbook upload $1

echo "Success!"
