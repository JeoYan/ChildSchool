package com.great.childschool.entity;

public class TjzTblquestion
{
	/**
	 * 考试题目实体类
	 * by 汤建志
	 */
	private int questionId;
	private String question;
	private String optionA;
	private String optionB;
	private String answer;
	private int safeId;
	private int testId;
	private String testName;
	private String myAnswer;


	public TjzTblquestion()
	{
	}

	public String getMyAnswer()
	{
		return myAnswer;
	}

	public void setMyAnswer(String myAnswer)
	{
		this.myAnswer = myAnswer;
	}

	public int getTestId()
	{
		return testId;
	}

	public void setTestId(int testId)
	{
		this.testId = testId;
	}

	public String getTestName()
	{
		return testName;
	}

	public void setTestName(String testName)
	{
		this.testName = testName;
	}

	public int getQuestionId()
	{
		return questionId;
	}

	public void setQuestionId(int questionId)
	{
		this.questionId = questionId;
	}

	public String getQuestion()
	{
		return question;
	}

	public void setQuestion(String question)
	{
		this.question = question;
	}

	public String getOptionA()
	{
		return optionA;
	}

	public void setOptionA(String optionA)
	{
		this.optionA = optionA;
	}

	public String getOptionB()
	{
		return optionB;
	}

	public void setOptionB(String optionB)
	{
		this.optionB = optionB;
	}

	public String getAnswer()
	{
		return answer;
	}

	public void setAnswer(String answer)
	{
		this.answer = answer;
	}

	public int getSafeId()
	{
		return safeId;
	}

	public void setSafeId(int safeId)
	{
		this.safeId = safeId;
	}


}
