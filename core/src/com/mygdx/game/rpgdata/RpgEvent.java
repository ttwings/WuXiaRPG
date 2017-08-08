package com.mygdx.game.rpgdata;

import java.util.ArrayList;

/**
 * Created by apple on 16/11/20.
 */
public class RpgEvent {
    public int id;//ID。
    public String name;//名称。
    int x;//地图 X 坐标。
    int y;//地图 Y 坐标。
    int pages;//事件页 RPG::Event::Page 的数组
    class Page{
        Condition condition;//事件指令的执行条件（RPG::Event::Page::Condition）。
        Graphic graphic;//事件页指定事件块的图片（RPG::Event::Page::Graphic）。
        int move_type;//移动类型（0：固定、1：随机、2：接近、3：自定义）。
        int move_speed;//移动速度（1：1/8 倍速、2：1/4 倍速、3：1/2 倍速、4：正常、5：2 倍速、6：4 倍速）。
        int move_frequency;//移动频率：（1：最低、2：低、3：正常、4：高、5：最高）。
        int move_route;//移动路线（RPG::MoveRoute）。只有当移动类型为「自定义」时才会调用。
        boolean walk_anime;//步行动画的开关。
        boolean step_anime;//踏步动画的开关。
        boolean direction_fix;//方向锁定的开关。
        boolean through;   //允许穿透的开关。
        int priority_type;//优先级（0：在普通角色下方、1：与普通角色同层、2：在普通角色上方）。
        int trigger;//触发条件（0：按确定键、1：玩家接触、2：事件接触、3：自动执行、4：并行处理）。
        ArrayList<RpgEventCommand>list;//执行内容。RPG::EventCommand 的数组。
        class Condition{

        }
        class Graphic{

        }
    }
}
