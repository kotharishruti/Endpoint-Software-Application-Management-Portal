#
# Cookbook Name:: mysql
# Recipe:: default
#
# Copyright 2016, YOUR_COMPANY_NAME
#
# All rights reserved - Do Not Redistribute
#
package 'mysql' do
  action :install
end

service 'mysql' do
  action [ :enable, :start ]
end
