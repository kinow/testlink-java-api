/*
 * The MIT License
 *
 * Copyright (c) <2011> <Mario Fuentes>
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package br.eti.kinoshita.testlinkjavaapi;

/**
 * @author Mario Fuentes - http://www.rhiscom.com
 * @since 1.9.4-1
 */
public enum TestLinkAPIErrors
{
	GeneralErrorCode(-1, ""),
	GeneralSuccessCode(1, ""),
	NotYetImplemented(50, ""),
	NoDevKey(100, ""),
	NoTestCaseId(110, ""),
	NoTestCaseExternalId(110, ""),
	NoTestPlanId(120, ""),
	NoBuildId(130, ""),
	NoTestMode(140, ""),
	NoStatus(150, ""),
	NoTestProjectId(160, ""),
	NoTestCaseName(170, ""),
	NoTestSuiteId(180, ""),
	MissingRequiredParameter(200, ""),
	ParameterNotInt(210, ""),
	NoTestSuiteName(220, ""),
	NodeIdIsNotInteger(230, ""),
	NodeIdDoesNotExist(231, ""),
	ConfigDeleteExecDisabled(232, ""),
	NoPlatformId(233, ""),
	NodeIdInvalidDataType(234, ""),
	InvalidAuth(2000, ""),
	InsufficientRights(2010, ""),
	InvalidTestPlanId(3000, ""),
	TestPlanIdNotInteger(3010, ""),
	NoBuildForTestPlanId(3020, ""),
	TestCaseIdNotInTestPlanId(3030, ""),
	TestPlanHasNoBuilds(3031, ""),
	BadBuildForTestPlan(3032, ""),
	TestPlanNameDoesNotExist(3033, ""),
	TestPlanNameAlreadyExists(3034, ""),
	PlatformNotLinkedToTestPlan(3040, ""),
	TestPlanHasNoPlatforms(3041, ""),
	TestCaseIdNotInTestPlanIdForPlatform(3042, ""),
	MissingPlatformIdButNeeded(3043, ""),
	PlatformIdNotLinkedToTestPlan(3044, ""),
	LinkedFeatureAlreadyExists(3045, ""),
	OtherVersionIsAlreadyLinked(3046, ""),
	InvalidBuildId(4000, ""),
	BuildIdNotInteger(4010, ""),
	BuildIdNoguess(4020, ""),
	BuildnameAlreadyExists(4030, ""),
	BuildnameDoesNotExist(4040, ""),
	InvalidTestCaseId(5000, ""),
	TestCaseIdNotInteger(5010, ""),
	TestCaseNameNotString(5020, ""),
	NoTestCaseByThisName(5030, ""),
	InvalidTestCaseExternalId(5040, ""),
	InvalidTestCaseVersionNumber(5050, ""),
	TestCaseVersionNumberKo(5051, ""),
	VersionNotValid(5052, ""),
	NoTestCaseFound(5053, ""),
	TestCaseEmptyName(5054, ""),
	TestCaseNameLenExceeded(5055, ""),
	TestCaseSiblingWithSameNameExists(5056, ""),
	InvalidStatus(6000, ""),
	AttachTempFileCreationError(6001, ""),
	AttachDbWriteError(6002, ""),
	AttachFeatureDisabled(6003, ""),
	AttachInvalidFk(6004, ""),
	AttachInvalidAttachment(6005, ""),
	InvalidTestProjectId(7000, ""),
	TestProjectNameSintaxError(7001, ""),
	TestProjectNameExists(7002, ""),
	TestProjectTestCasePrefixExists(7003, ""),
	TestProjectTestCasePrefixIsEmpty(7004, ""),
	TestProjectTestCasePrefixIsTooLong(7005, ""),
	TestPlanTestProjectKo(7006, ""),
	TestCaseTestProjectKo(7007, ""),
	TestProjectIsEmpty(7008, ""),
	TestProjectPrefixAlreadyExists(7009, ""),
	ReqSpecTestProjectKo(7010, ""),
	TestProjectNameDoesNotExist(7011, ""),
	TestProjectCopySourcenameDoesNotExist(7012, ""),
	InvalidTestSuiteId(8000, ""),
	TestSuiteDoNotBeLongToTestProject(8001, ""),
	TestSuiteNameNotString(8002, ""),
	InvalidParentTestSuiteId(8003, ""),
	NoCustomFieldByThisName(9000, ""),
	CustomFieldNotAppForNodeType(9001, ""),
	CustomFieldHasNotDesignScope(9002, ""),
	CustomFieldNotAssignedToTestProject(9003, ""),
	NoUserByThisLogin(10000, ""),
	ReqSpecKo(11000, ""),
	ReqSpecIsEmpty(11001, ""),
	ReqReqSpecKo(11002, ""),
	ReqKo(11003, "");
	
	private Integer code;
	private String message;
	
	private TestLinkAPIErrors(int code, String message)
	{
		this.code = code;
		this.message = message;
	}
	
	public Integer getCode()
	{
		return this.code;
	}
	
	@Override
	public String toString()
	{
		return this.code.toString() + ": " + this.message;
	}
	
	public boolean isCode(Integer code)
	{
		return this.code.equals(code);
	}
}
