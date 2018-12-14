#include <Wire.h>
 
#define SLAVE_ADDRESS 0x18
int number = 0;
int sensorPin = 3;
int motorPin = A0; 
int blinkPin = 13;
int Sys = 1;
int turnonlight = 0;
int turnonpump = 0;

void setup(){
  pinMode(motorPin, OUTPUT); // set A0 to an output so we can use it to turn on the transistor
  pinMode(blinkPin, OUTPUT);
  Serial.begin(9600);
  Wire.begin(SLAVE_ADDRESS);
  Wire.onReceive(receiveData);
  Wire.onRequest(sendData);
 
}

void loop(){
  int soil = analogRead(A1);
  int light = analogRead(A2);

  int reading = analogRead(sensorPin);
 
  float voltage = reading * 5.0;
  voltage /= 1024.0;
  float tempC = (voltage - 0.5) * 100;
  if(Sys == 1){
  
    Serial.print("T:");
    Serial.println(int(tempC));
    Serial.print("S:");
    Serial.println(soil);
    Serial.print("L:");
    Serial.println(light);
  
 //number = 1. system
 //number = 2, water
 //number = 3 turn on light, 
    if(turnonpump != 1){
      if(soil > 250){
         digitalWrite(motorPin, HIGH); // turn on the motor
         delay(1000);
      }
    
      if(soil < 250){
         digitalWrite(motorPin, LOW); // turn on the motor
         delay(1000);
      }
     
    }
    if(turnonpump ==1){
       digitalWrite(motorPin, HIGH); // turn on the motor
       delay(3000);
       digitalWrite(motorPin, LOW);
       turnonpump = 0;
       
    }
    
    if(turnonlight == 0){
      if(light > 450){
         digitalWrite(blinkPin, HIGH); // turn on the LED
         delay(200);
      }
      
      if(light <= 450){
         digitalWrite(blinkPin, LOW); // turn on the LED
         delay(200);
      }
    }
    if(turnonlight ==1){
      digitalWrite(blinkPin, HIGH);
    }
  delay(200);
  }
}
void receiveData(int byteCount) {

  while (Wire.available()) {
     number = Wire.read();
     delay(500);
     Serial.println(number);
     if(number == 1){
       Sys = 1;
     }
     else if(number == 3){
       if(turnonlight == 1){
         digitalWrite(blinkPin, LOW);
         turnonlight = 0;
         delay(500);
       }
       else if(turnonlight ==0){
         turnonlight = 1;
         delay(1000);
       }
     }
     
     else if(number == 2){
       if(turnonpump == 0){
         turnonpump = 1;
       }
     }
     else{
       Sys = 0;
     }
     
     
   

  }
}
void sendData(){
    Wire.write(number);
}
