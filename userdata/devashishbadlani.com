--- 
customlog: 
  - 
    format: combined
    target: /usr/local/apache/domlogs/devashishbadlani.com
  - 
    format: "\"%{%s}t %I .\\n%{%s}t %O .\""
    target: /usr/local/apache/domlogs/devashishbadlani.com-bytes_log
documentroot: /home/deebee07/public_html
group: deebee07
hascgi: 1
homedir: /home/deebee07
ifmoduleitkc: {}

ifmodulemodincludec: 
  directoryhomedeebeepublichtml: 
    ssilegacyexprparser: 
      - 
        value: " On"
ip: 107.180.2.134
owner: gdresell
phpopenbasedirprotect: 1
port: 80
scriptalias: 
  - 
    path: /home/deebee07/public_html/cgi-bin
    url: /cgi-bin/
  - 
    path: /home/deebee07/public_html/cgi-bin/
    url: /cgi-bin/
serveradmin: webmaster@devashishbadlani.com
serveralias: www.devashishbadlani.com
servername: devashishbadlani.com
usecanonicalname: 'Off'
user: deebee07
userdirprotect: ''
