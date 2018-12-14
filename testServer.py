import _thread
from smbus import SMBus
import socket
import time
import serial
from BrickPi import *
import re
import math
import http.client, urllib.parse

ser = serial.Serial('/dev/ttyACM0', 9600)
arduino_addr = 0x18
bus = SMBus(1)
i = 0

addr = 0x18
bus = SMBus(1)

host = ''
port = 1024

def main():
    listen_skt = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    listen_skt.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR,1)
    listen_skt.bind(('', port))
    listen_skt.listen(1)
    print('start listening...')
    
    _thread.start_new_thread(handler, (listen_skt, ))
    _thread.start_new_thread(getdata, (1, ))

    
def handler(listen_skt):
    while 1:
        connection_skt, addr = listen_skt.accept()
        data = connection_skt.recv(1024)
        if data:
            print(data)
            data = data.split(b'\r\n\r\n')
            data = data[-1]
            data1 = data.decode('utf-8')
            data2 = data1.split('=', 1)
            data4 = data2[1]
            data3 = data2[0]
            data4 = data3.replace("+"," ");
            print('data4', data4)
            if(data4 =='water'):
                send = 2
            if(data4=='flower'):
                send = 1
            if(data4=='light'):
                send = 3
            if(data4=='diagnose'):
                ##time.sleep(10)
                connection_skt.send("HTTP/1.1 200 LATE BLIGHT\r\nContent-Type: application/text\r\nContent-Length: 10\r\n\r\n{'response'}".encode('utf-8'))
            connection_skt.close()
                
            
                
            try:
                bus.write_byte(0x18,send)
            except Exception as e:
                print(e)
                
            time.sleep(2)
            
##            connection_skt.send(.encode('utf-8'))
        
def getdata(index):
    soil = 0
    light = 0
    temp = 0
    time.sleep(3)
    while 1:
        if(ser.in_waiting > 0):
            Arec = ser.readline()
            ArduinoSignal = Arec.decode()
            if(ArduinoSignal[0] == 'S'):
                soil = int(''.join(filter(str.isdigit,ArduinoSignal)))
            if(ArduinoSignal[0] == 'L'):
                light = int(''.join(filter(str.isdigit,ArduinoSignal)))
            if(ArduinoSignal[0] == 'T'):
                temp = int(''.join(filter(str.isdigit,ArduinoSignal)))
            params1 = urllib.parse.urlencode({'field1': soil, 'key':'BL0FA92AWWWK2P4D'})
            params2 = urllib.parse.urlencode({'field1': temp, 'key':'V80VUUTWZUZ9EN5X'})
            params3 = urllib.parse.urlencode({'field1': light, 'key':'NEKCINJ31C0A6QW2'})
            headers = {"Content-typZZe": "application/x-www-form-urlencoded","Accept": "text/plain"}
            try:
                conn = http.client.HTTPConnection("api.thingspeak.com:80")
                conn.request("POST", "/update", params1, headers)
                r1 = conn.getresponse()
                print (r1)
                print(r1.status)
                print(r1.reason)
                data = r1.read()
                conn.close()
            except:
                print ("connection failed")
            try:
                conn2 = http.client.HTTPConnection("api.thingspeak.com:80")
                conn2.request("POST", "/update", params2, headers)
                r2 = conn2.getresponse()
                print (r2)
                print(r2.status)
                print(r2.reason)
                data = r2.read()
                conn2.close()
            except:
                print ("connection failed")
            try:
                conn3 = http.client.HTTPConnection("api.thingspeak.com:80")
                conn3.request("POST", "/update", params3, headers)
                r3 = conn3.getresponse()
                print (r3)
                print(r3.status)
                print(r3.reason)
                data = r3.read()
                conn3.close()
            except:
                print ("connection failed")
            print('soil ', soil)
            print('temp ',temp)
            print('light',light)
            time.sleep(10)
##        time.sleep(2.5)


main()



##    i=i+1
##    bus.write_byte(arduino_addr,1)
##    time.sleep(1)
    
    