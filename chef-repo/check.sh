#!/bin/bash

cd /home/esamp/chef-repo

knife ssh 1.1.5.22 'yum search '$1'' --manual-list --ssh-user root --ssh-password fedora
