/*Copyright 2014 Computer Vision Lab

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.*/

/**
 * A Pig script that defines the common package UDFs.
 * @author Rodrigo Ferreira
 */

--Eval UDFs
DEFINE II_Classify br.puc_rio.ele.lvc.interimage.common.udf.Classify;
DEFINE II_Combine br.puc_rio.ele.lvc.interimage.common.udf.Combine;
DEFINE II_Max br.puc_rio.ele.lvc.interimage.common.udf.Max;
DEFINE II_Mean br.puc_rio.ele.lvc.interimage.common.udf.Mean;
DEFINE II_Min br.puc_rio.ele.lvc.interimage.common.udf.Min;
DEFINE II_Mul br.puc_rio.ele.lvc.interimage.common.udf.Mul;
DEFINE II_Sum br.puc_rio.ele.lvc.interimage.common.udf.Sum;
DEFINE II_ToClassification br.puc_rio.ele.lvc.interimage.common.udf.ToClassification;
DEFINE II_ToProps br.puc_rio.ele.lvc.interimage.common.udf.ToProps;

--Filter UDFs

--Special UDFs
