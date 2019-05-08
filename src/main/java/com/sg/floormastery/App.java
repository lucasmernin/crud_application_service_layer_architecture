package com.sg.floormastery;

import com.sg.floormastery.controller.FMController;
import com.sg.floormastery.dao.FMDao;
import com.sg.floormastery.dao.FMDaoImpl;
import com.sg.floormastery.service.FMService;
import com.sg.floormastery.service.FMServiceImpl;
import com.sg.floormastery.ui.FMUserIO;
import com.sg.floormastery.ui.FMUserIOImpl;
import com.sg.floormastery.ui.FMView;

/**
 *
 * @author lukem
 */
public class App {
    
    public static void main(String[] args) {
       
    FMUserIO io = new FMUserIOImpl();
    FMView view = new FMView(io);
    FMDao dao = new FMDaoImpl();
    FMService service = new FMServiceImpl(dao);
    FMController controller = new FMController(view, service);

    controller.run();
    
    }
}

