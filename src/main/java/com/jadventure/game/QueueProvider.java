package com.jadventure.game;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import lombok.SneakyThrows;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QueueProvider {
    private static Logger logger = LoggerFactory.getLogger(QueueProvider.class);
    public static BlockingQueue<String> queue = new LinkedBlockingQueue<>();
    public static BlockingQueue<String> inputQueue = new LinkedBlockingQueue<>();
    public static DataOutputStream out;
    public static DataInputStream in;
    public static GameModeType mode;
    public static Socket server;
    public static Configuration config = getConfig();

    /**
     * 获取配置信息
     */
    @SneakyThrows
    public static Configuration getConfig() {
        try {
            String relativelyPath = System.getProperty("user.dir");//获取当前项目的根目录
            return new PropertiesConfiguration(relativelyPath
                    + File.separator+"src"
                    + File.separator+"main"
                    + File.separator+"resources"
                    + File.separator+"international"
                    + File.separator+"cnGameDes.properties"
            );
        } catch (Exception e) {
            throw new DeathException("获取配置文件失败，"+e.getMessage());
        }
    }

    public static void startMessenger(GameModeType modeInc, Socket sockerInc) {
        logger.debug("startMessenger( " + modeInc + " , " + sockerInc + " )");
        mode = modeInc;
        server = sockerInc;
    }

    public static void startMessenger(GameModeType modeInc) {
        logger.debug("startMessenger( " + modeInc + " )");
        mode = modeInc;
    }

    public static BlockingQueue<String> getQueue() {
        return queue;
    }

    public static void offer(String message) {
        logger.debug("offer( " + message + " )");

        if (GameModeType.SERVER == mode) {
            try {
                out = new DataOutputStream(server.getOutputStream());
                in = new DataInputStream(server.getInputStream());
            } catch (IOException ioe) {
                logger.debug("Inside offer( " + message + " )", ioe);
            }
        }

        if (GameModeType.SERVER == mode) {
            sendToServer(message);
        } else {
            System.out.println(message);
        }
    }

    public static boolean sendToServer(String message) {
        logger.debug("sendToServer( " + message + " )");
        try {
            out.writeUTF(message + "END");
        } catch(SocketException se) {
            logger.debug("Inside  sendToServer( " + message + " )", se);
            return false;
        } catch(IOException ioe) {
            logger.debug("Inside  sendToServer( " + message + " )", ioe);
            return false;
        }
        return true;
    }

    public static String getInput(String message) {
        logger.debug("getInput( " + message + " )");
        String input = "";
        try {
            out.writeUTF(message + "END");
            input = in.readUTF();
        } catch(SocketException se) {
            logger.debug("Inside getInput( " + message + " )", se);
            input = "error";
        } catch(IOException ioe) {
            logger.debug("Inside getInput( " + message + " )", ioe);
            input = "error";
        }
        return input;
    }

    public static String take() {
        String message = null;
        if (GameModeType.SERVER == mode) {
            message = getInput("QUERY");
            if (message.equals("error")) {
                message = "exit";
            }
        } else {
        	Scanner input = null;
        	try {
	            input = new Scanner(System.in);
	            message = input.nextLine();
        	}
        	catch (NoSuchElementException nsee) {
        	    nsee.printStackTrace();
        	}
        	catch (IllegalStateException ise) {
                ise.printStackTrace();
        	}
        }
        return message;
    }
}
