/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class solver_fluctuat_Fluctuat */

#ifndef _Included_solver_fluctuat_Fluctuat
#define _Included_solver_fluctuat_Fluctuat
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     solver_fluctuat_Fluctuat
 * Method:    createDebugInterface
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_solver_fluctuat_Fluctuat_createDebugInterface
  (JNIEnv *, jclass);

/*
 * Class:     solver_fluctuat_Fluctuat
 * Method:    debugAddSourceFile
 * Signature: (JLjava/lang/String;)Z
 */
JNIEXPORT jboolean JNICALL Java_solver_fluctuat_Fluctuat_debugAddSourceFile
  (JNIEnv *, jclass, jlong, jstring);

/*
 * Class:     solver_fluctuat_Fluctuat
 * Method:    debugSetResourceFile
 * Signature: (JLjava/lang/String;)Z
 */
JNIEXPORT jboolean JNICALL Java_solver_fluctuat_Fluctuat_debugSetResourceFile
  (JNIEnv *, jclass, jlong, jstring);

/*
 * Class:     solver_fluctuat_Fluctuat
 * Method:    debugAddFunctionBreakPoint
 * Signature: (JLjava/lang/String;J)Z
 */
JNIEXPORT jboolean JNICALL Java_solver_fluctuat_Fluctuat_debugAddFunctionBreakPoint
  (JNIEnv *, jclass, jlong, jstring, jlong);

/*
 * Class:     solver_fluctuat_Fluctuat
 * Method:    debugAddBreakPoint
 * Signature: (JLjava/lang/String;IJ)Z
 */
JNIEXPORT jboolean JNICALL Java_solver_fluctuat_Fluctuat_debugAddBreakPoint
  (JNIEnv *, jclass, jlong, jstring, jint, jlong);

/*
 * Class:     solver_fluctuat_Fluctuat
 * Method:    debugDelBreakPoints
 * Signature: (J)Z
 */
JNIEXPORT jboolean JNICALL Java_solver_fluctuat_Fluctuat_debugDelBreakPoints
  (JNIEnv *, jclass, jlong);

/*
 * Class:     solver_fluctuat_Fluctuat
 * Method:    debugAnalyze
 * Signature: (JLjava/lang/String;JJ)I
 */
JNIEXPORT jint JNICALL Java_solver_fluctuat_Fluctuat_debugAnalyze
  (JNIEnv *, jclass, jlong, jstring, jlong, jlong);

/*
 * Class:     solver_fluctuat_Fluctuat
 * Method:    debugContinue
 * Signature: (JI)I
 */
JNIEXPORT jint JNICALL Java_solver_fluctuat_Fluctuat_debugContinue
  (JNIEnv *, jclass, jlong, jint);

/*
 * Class:     solver_fluctuat_Fluctuat
 * Method:    debugNext
 * Signature: (JI)I
 */
JNIEXPORT jint JNICALL Java_solver_fluctuat_Fluctuat_debugNext
  (JNIEnv *, jclass, jlong, jint);

/*
 * Class:     solver_fluctuat_Fluctuat
 * Method:    debugStep
 * Signature: (JI)I
 */
JNIEXPORT jint JNICALL Java_solver_fluctuat_Fluctuat_debugStep
  (JNIEnv *, jclass, jlong, jint);

/*
 * Class:     solver_fluctuat_Fluctuat
 * Method:    debugStepInstruction
 * Signature: (JI)I
 */
JNIEXPORT jint JNICALL Java_solver_fluctuat_Fluctuat_debugStepInstruction
  (JNIEnv *, jclass, jlong, jint);

/*
 * Class:     solver_fluctuat_Fluctuat
 * Method:    getLineStatus
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_solver_fluctuat_Fluctuat_getLineStatus
  (JNIEnv *, jclass);

/*
 * Class:     solver_fluctuat_Fluctuat
 * Method:    getSourceStatus
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_solver_fluctuat_Fluctuat_getSourceStatus
  (JNIEnv *, jclass);

/*
 * Class:     solver_fluctuat_Fluctuat
 * Method:    getPointStatus
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_solver_fluctuat_Fluctuat_getPointStatus
  (JNIEnv *, jclass);

/*
 * Class:     solver_fluctuat_Fluctuat
 * Method:    debugGetVariable
 * Signature: (JLjava/lang/String;)J
 */
JNIEXPORT jlong JNICALL Java_solver_fluctuat_Fluctuat_debugGetVariable
  (JNIEnv *, jclass, jlong, jstring);

/*
 * Class:     solver_fluctuat_Fluctuat
 * Method:    debugFromCurrentRetrieveResult
 * Signature: (JJ[D)Z
 */
JNIEXPORT jboolean JNICALL Java_solver_fluctuat_Fluctuat_debugFromCurrentRetrieveResult
  (JNIEnv *, jclass, jlong, jlong, jdoubleArray);

/*
 * Class:     solver_fluctuat_Fluctuat
 * Method:    debugFromCurrentSetResult
 * Signature: (JJ[D)Z
 */
JNIEXPORT jboolean JNICALL Java_solver_fluctuat_Fluctuat_debugFromCurrentSetResult
  (JNIEnv *, jclass, jlong, jlong, jdoubleArray);

/*
 * Class:     solver_fluctuat_Fluctuat
 * Method:    debugClearAnalyzer
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_solver_fluctuat_Fluctuat_debugClearAnalyzer
  (JNIEnv *, jclass, jlong);

#ifdef __cplusplus
}
#endif
#endif
/* Header for class solver_fluctuat_Fluctuat_DebugResult */

#ifndef _Included_solver_fluctuat_Fluctuat_DebugResult
#define _Included_solver_fluctuat_Fluctuat_DebugResult
#ifdef __cplusplus
extern "C" {
#endif
#ifdef __cplusplus
}
#endif
#endif
/* Header for class solver_fluctuat_Fluctuat_DebugStatus */

#ifndef _Included_solver_fluctuat_Fluctuat_DebugStatus
#define _Included_solver_fluctuat_Fluctuat_DebugStatus
#ifdef __cplusplus
extern "C" {
#endif
#ifdef __cplusplus
}
#endif
#endif
