#!/bin/bash

echo $1 #path

echo "Creating recipe"

echo ''>default_1.rb
echo ''>/home/esamp/chef-repo/cookbooks/default_1/recipes/default.rb

cat template_1.rb>default_1.rb

grep -rl path default_1.rb | xargs sed -i "s#path#$1#g"

echo "Creating cookbook"

#cd /home/esamp/chef-repo/

#knife cookbook create default_1

cd /home/esamp/chef-repo/cookbooks/default_1/recipes

cat /home/esamp/chef-repo/default_1.rb>>default.rb

echo "Uploading cookbook"

knife cookbook upload default_1

echo "Success!"
