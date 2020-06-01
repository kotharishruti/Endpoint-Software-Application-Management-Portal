#
# Cookbook Name:: lighttpd
# Recipe:: default
#
# Copyright 2016, YOUR_COMPANY_NAME
#
# All rights reserved - Do Not Redistribute
#
package 'lighttpd' do
  action :install
end

service 'lighttpd' do
  action [ :enable, :start ]
end
