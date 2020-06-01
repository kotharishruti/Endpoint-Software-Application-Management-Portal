#
# Cookbook Name:: abcd
# Recipe:: default
#
# Copyright 2016, YOUR_COMPANY_NAME
#
# All rights reserved - Do Not Redistribute
#
package 'abcd' do
  action :install
end

service 'abcd' do
  action [ :enable, :start ]
end
