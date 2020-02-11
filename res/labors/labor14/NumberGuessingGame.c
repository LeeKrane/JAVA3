#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main() {
	int num, val;
	
	srand(time(NULL));
	num = rand() % 1000 + 1;
	
	printf("NumberGuessingGame\n");
	fflush(stdout);
	
	while (1) {
		scanf("%d", &val);
		
		if (val < num)
			printf("too low\n");
		else if (val > num)
			printf("too high\n");
		else {
			printf("correct\n");
			return 0;
		}
		fflush(stdout);
	}
}