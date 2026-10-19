#include<iostream>
using namespace std;
int main() {
	int a,b;
	int wynik = 0;
	float wynik2 = 0;
	
	cout<<"podaj liczbę a";
	cin>>a;
		cout<<"podaj liczbę b";
	cin>>b;
	
	wynik = a + b;
	cout<<"wynik dodawania"<<wynik<<endl;
		wynik = a - b;
	cout<<"wynik odejmowania"<<wynik<<endl;
			wynik = a * b;
	cout<<"wynik mnożenia"<<wynik<<endl;
	
	if(b==0) {
		cout<<"pamiętaj cholero nie dziel przez ZERO!"<<endl;
	} else 
	{ wynik2 = (float)a / (float)b;
} 
cout<<"wynik dzielenia"<<wynik2<<endl;
	
}
