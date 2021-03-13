#include "src_Rnd.h"
#include <jni.h>
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <time.h>
#include <math.h>

unsigned long long tm = 0;

JNIEXPORT jbooleanArray JNICALL Java_src_Rnd_createShuffledArray (JNIEnv* env, jobject thisObj, jint N, jdouble p) {

	if(tm == 0) {
		tm = (unsigned long long)time(NULL);
	}

	srand(tm);
	jboolean* shuffled = malloc(N * sizeof(jboolean));

	for(int i = 0; i < N; i++) {
		if(i < round(N * p)) {
			shuffled[i] = true;
		} else {
			shuffled[i] = false;
		}
	}

	for(int i = N - 1; i >=1; i--) {
		int j = rand() % (i + 1);
		jboolean temp = shuffled[j];
		shuffled[j] = shuffled[i];
		shuffled[i] = temp;
	}

	jdoubleArray out = (*env)->NewBooleanArray(env, N);
	if (out == NULL) return NULL;
	(*env)->SetBooleanArrayRegion(env, out, 0, N, shuffled);

	return out;
}
