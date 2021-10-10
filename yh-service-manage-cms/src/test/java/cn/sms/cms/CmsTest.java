package cn.sms.cms;

import com.yh.cms.ManageCmsApplication;
import com.yh.cms.dao.CmsPageRepository;
import com.lxw.framework.domain.cms.CmsPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ManageCmsApplication.class)
public class CmsTest {

    @Autowired
    private CmsPageRepository cmsPageRepository;

    /**
     * 添加方法
     */
    @Test
    public void save(){
        CmsPage cmsPage = new CmsPage();
        cmsPage.setPageName("张三");
        cmsPage.setSiteId("1");
        CmsPage cms = cmsPageRepository.save(cmsPage);
        System.out.println(cms);
    }


    /**
     * 修改方法
     */
    @Test
    public void update(){
        //先根据id查询
        Optional<CmsPage> optional = cmsPageRepository.findById("6152fbd386ef7be4e094b29f");
        if (optional.isPresent()){
            CmsPage cmsPage = optional.get();
            cmsPage.setPageName("test");
            CmsPage save = cmsPageRepository.save(cmsPage);
            System.out.println(save);
        }
    }

    /**
     * 删除
     */
    @Test
    public void delete(){
        cmsPageRepository.deleteById("6152fbd386ef7be4e094b29f");
    }

    /**
     *测试自定义方法
     */
    @Test
    public void test01(){
        CmsPage cmsPage = cmsPageRepository.findByPageName("index.html");
        System.out.println(cmsPage);
    }
}
