package com.assignment.leaderboard.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.NonNull;

public class LeaderboardException extends Exception
{
	/**
	 * Serializable version UID
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger log = LoggerFactory.getLogger(LeaderboardException.class);

	/**
	 * Constructor for SearchIndexKernelException.
	 */
	public LeaderboardException()
	{
		super();
	}

	/**
	 * Parameterized Constructor for SearchIndexKernelException. Take message as an
	 * input
	 * 
	 * @param message
	 */
	public LeaderboardException(String message)
	{
		super(message);
	}

	/**
	 * Exception constructor with message and Throwable param
	 * 
	 * @param message
	 * @param e
	 */
	public LeaderboardException(Throwable e)
	{
		super(e);
	}

	/**
	 * Exception constructor with message and Throwable param
	 * 
	 * @param message
	 * @param e
	 */
	public LeaderboardException(String message, Throwable e)
	{
		super(message, e);
	}

	/**
	 * Constructs a LeaderboardException for the given activityContext and
	 * exceptionMessage; logs the created exception and activityContext as an ERROR;
	 * and returns the exception for further handling (eg throwing).
	 * <p>
	 * The log message will be in the format:
	 * <code>[activityContext] - exceptionMessage with stack trace</code> .
	 * 
	 * @param activityContext  activityContext for the purposes of logging
	 * @param exceptionMessage message used for the construction of the
	 *                         LeaderboardException and in logging
	 * @return LeaderboardException exception instance that was created and logged
	 */
	public static LeaderboardException reportServiceException(@NonNull String activityContext,
			@NonNull String exceptionMessage)
	{
		LeaderboardException exception = new LeaderboardException(exceptionMessage);

		log.error("[{}] - ", activityContext, exception);

		return exception;
	}

	/**
	 * Constructs a LeaderboardException for the given activityContext,
	 * exceptionMessage and cause exception; logs the created exception and
	 * activityContext as an ERROR; and returns the exception for further handling
	 * (eg throwing).
	 * <p>
	 * The log message will be in the format:
	 * <code>[activityContext] - exceptionMessage with stack trace and cause stack trace</code>
	 * .
	 * 
	 * @param activityContext  activityContext for the purposes of logging
	 * @param exceptionMessage message used for the construction of the
	 *                         LeaderboardException and in logging
	 * @param exceptionCause   exception causing the creation of this
	 *                         LeaderboardException
	 * @return LeaderboardException exception instance that was created and logged
	 */
	public static LeaderboardException reportServiceException(@NonNull String activityContext,
			@NonNull String exceptionMessage, Throwable exceptionCause)
	{
		LeaderboardException exception = new LeaderboardException(exceptionMessage, exceptionCause);

		log.error("[{}] - ", activityContext, exception);

		return exception;
	}

}
