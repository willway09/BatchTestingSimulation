#include "src_Rnd.h"
#include <jni.h>
#include <cmath>
#include <ranluxpp.h>

//Seed generated from Random.org (https://www.random.org/integers/) in a range from 0 to 1,000,000
ranluxpp generator(456491);

JNIEXPORT jbooleanArray JNICALL Java_src_Rnd_createShuffledArray (JNIEnv* env, jobject thisObj, jint N, jdouble p) {
	jboolean* shuffled = new jboolean[N]; //malloc(N * sizeof(jboolean));

	for(int i = 0; i < N; i++) {
		if(i < std::round(N * p)) {
			shuffled[i] = true;
		} else {
			shuffled[i] = false;
		}
	}

	
	float* randomValues = new float[N];
	generator.getarray(N, randomValues);

	//Shuffle values around
	for(int i = N - 1; i >=1; i--) {
		int j = randomValues[i] * (i + 1); //Scale from [0,1) to [0,i] | (input range does not contain 1, so have to add 1 to i to make output range include i)
		jboolean temp = shuffled[j];
		shuffled[j] = shuffled[i];
		shuffled[i] = temp;
	}

	delete[] randomValues;

	jbooleanArray out = (env)->NewBooleanArray(N);
	if (out == NULL) return NULL;
	(env)->SetBooleanArrayRegion(out, 0, N, shuffled);

	delete[] shuffled; //free(shuffled);

	//Increment so that a new seed comes next time

	return out;
}



JNIEXPORT jfloatArray JNICALL Java_src_Rnd_generateRandomArray (JNIEnv* env, jobject thisObj, jint N) {
	float* randomValues = new float[N]; //Admittedly duplicated from above, but function calls are costly, so creating a function would be undesirable
	generator.getarray(N, randomValues);


	jfloatArray out = env->NewFloatArray(N);
	if (out == NULL) return NULL;
	env->SetFloatArrayRegion(out, 0, N, randomValues);


	delete[] randomValues;

	return out;
}
