package mk.ukim.finki.diettastic;

import mk.ukim.finki.diettastic.model.*;
import mk.ukim.finki.diettastic.model.enums.DietType;
import mk.ukim.finki.diettastic.model.enums.Role;
import mk.ukim.finki.diettastic.repository.*;
import mk.ukim.finki.diettastic.service.PostService;
import mk.ukim.finki.diettastic.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class DiettasticApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext =
                SpringApplication.run(DiettasticApplication.class, args);

        UserRepository userRepository = configurableApplicationContext.getBean(UserRepository.class);
        GoalRepository goalRepository = configurableApplicationContext.getBean(GoalRepository.class);
        DietRepository dietRepository = configurableApplicationContext.getBean(DietRepository.class);
        MealRepository mealRepository = configurableApplicationContext.getBean(MealRepository.class);
        UserService userService = configurableApplicationContext.getBean(UserService.class);
        PostService postService = configurableApplicationContext.getBean(PostService.class);
        // =============================================================================================================
        // Diets and Meals
        Meal meal1 = new Meal("Chai Spice Cheesecake", DietType.VEGAN, 7.5f, "1 tspn Ginger/2 tspns Chai Spice Mix/100g brown sugar/1oz Heavy Cream/2 eggs/2 tbsp dry milk",  "Blend cheesecake-batter ingredients/Pour in batter/Bake until top is lightly puffed/Cool to room temperature", "https://images.eatsmarter.com/sites/default/files/styles/max_size/public/chai-spiced-cheesecake-532130.jpg", "https://www.mymoderncookery.com/wp-content/uploads/2015/11/Lara-bar-7-of-7-1024x678.jpg");
        Meal meal2 = new Meal("Vegan Tomato Soup", DietType.VEGAN, 9.0f, "1 tspn Olive oil/800g tomatoes/1 cup water/1 tbsp paprika/2 eggs/salt and peper",  "Heat olive oil in a pot over low heat/Add garlic and cook until fragrant/Bring to a boil, reduce heat, and simmer until tomatoes have broken down and soup starts to thicken./Puree tomato soup/Reheat soup before serving", "https://www.blissfulbasil.com/wp-content/uploads/2014/01/Dreamy-Vegan-Tomato-Soup-7011-500x500.jpg", "https://avirtualvegan.com/wp-content/uploads/2015/11/tomato-basil-soup-3-resized-3.jpg");
        Meal meal3 = new Meal("Vegetarian Quiche", DietType.VEGETARIAN, 8.0f, "2 eggs/100g butter/200g Parmesan and Cheddar cheese/1 cup milk/salt and peper",
                "Bake pastry shell/Melt butter in a large skillet/ Beat eggs in a bowl, stir in milk, Parmesan cheese, and Cheddar cheese./Carefully pour egg /Bake in preheated oven",
                "https://imagesvc.meredithcorp.io/v3/mm/image?url=https%3A%2F%2Fimages.media-allrecipes.com%2Fuserphotos%2F7582923.jpg", "https://theloopywhisk.com/wp-content/uploads/2019/01/Broccoli-Quiche_730px-featured.jpg");
        Meal meal4 = new Meal("Vegetarian Mexican Lasagna", DietType.VEGETARIAN, 8.7f, "2 cups Olive oil/1 onion/1 garlic/100g cheese/salt and peper/300ml milk/12 tortillas",
                "Heat olive oil in a large skillet/Add onion, jalapenos, bell pepper, and garlic./Combine 2 cups pepper Jack cheese, ricotta cheese, spinach, egg/Add a layer of 6 tortillas and follow with remaining vegetables/Bake for 45 minutes to 1 hour",
        "https://imagesvc.meredithcorp.io/v3/mm/image?url=https%3A%2F%2Fimages.media-allrecipes.com%2Fuserphotos%2F7687154.jpg&q=85", "https://amindfullmom.com/wp-content/uploads/2020/08/Mexican-Lasagna-with-tortillas.jpg");
        Meal meal5 = new Meal("Chicken Curry", DietType.NORMAL, 8.0f, "500g chicken/1 cup Olive oil/garlic and ginger/2 tbsp curry/salt and peper/2 cups milk",
                "Heat oil in a large, deep skillet over medium heat./Add garlic and ginger./Pour in the milk and water and let simmer for 3 to 4 minutes and add chicken, stirring occasionally, for 20 minutes.",
                "https://www.kitchensanctuary.com/wp-content/uploads/2020/08/Easy-Chicken-Curry-square-FS-117.jpg", "https://simply-delicious-food.com/wp-content/uploads/2021/03/Easy-chicken-curry-3.jpg");
        Meal meal6 = new Meal("Caesar Salad", DietType.NORMAL, 9.5f, "lettuce/1 cup Olive oil/garlic and ginger/1 tbsp vinegar/salt and peper/1 tbsp lemon juice/1 tbsp mustard/grated cheese",
                "Clean lettuce thoroughly and wrap in paper towels to absorb moisture./Combine oil, vinegar, Worcestershire sauce, salt, mustard, garlic and lemon juice./Place torn lettuce leaves in a large bowl./Pour dressing over the top and toss lightly.",
                "https://thedinnerbell.recipes/wp-content/uploads/2019/09/Garlic-Caesar-Salad-Recipe-4.jpg", "https://basicsmarket.com/wp-content/uploads/2019/05/Basics-1025.jpg");
        Meal meal7 = new Meal("Dukan Diet Turkey Meatballs", DietType.NORMAL, 7.0f, "680g ground turkey/2 cloves garlic/1 onion/2 egg whites/100g cheese/1 tsp dried oregano",
                "Combine all the ingredients in a medium-sized bowl./Scoop out one tablespoon of mixture for each meatball and place formed meatballs on a parchment lined baking sheet./Broil meatballs until browned, turning once.",
                "https://www.drdukanrecipes.com/wp-content/uploads/2012/09/Barbecue-Meatballs-620x465.jpg", "https://www.skinnykitchen.com/wp-content/uploads/2015/02/buffalo-turkey-meatballs-photo-e1423942561380.jpg");

        Meal meal8 = new Meal("Dukan Salmon and Broccoli Tabbouleh", DietType.NORMAL, 8.4f, "1 cup broccoli florets/parsley and mint/2 onions/1 salmon fillet/100g cheese/salt and pepper to taste",
                "Steam or blanch the broccoli in boiling water then refresh under cold running water./Fluff the oat bran with a fork then stir in the chopped herbs, spring onion, lime zest, broccoli and seasoning./Grill the salmon and serve with the tabbouleh.",
                "https://www.nospoonnecessary.com/wp-content/uploads/2018/08/Summer-Corn-Broccoli-Tabbouleh-75-1-500x375.jpg", "https://gourmetcasakitchenblog.files.wordpress.com/2017/02/image2.jpg?w=950");
        Meal meal9 = new Meal("Spicy Dukan Shrimp", DietType.NORMAL, 8.8f, "1 jalapeno/1 lemon/2 cloves garlic/1 pound prawns/1 tbsp Sriracha/1 tbsp greek yogurt",
                "Put lemon juice and zest, brandy, garlic, Sriracha and spices in a QuicKMix, and shake until blended./Pour mix over prawns and stir in./Remove prawns to 2 plates and whisk Faux Fromage Frais into the sauce that's left in the Steamcase.", "https://media.dukandiet.com/recipes/20137.jpg", "https://www.drdukanrecipes.com/wp-content/uploads/2012/09/cajun-shrimp1-620x464.jpg");
        Meal meal10 = new Meal("Satay sweet potato curry", DietType.VEGAN, 9.0f, "1 tbsp coconut oil/2 cloves garlic/1 onions/3 tbsp Thai red curry paste/500g sweet potato/200g bag spinach/1 lime/1 tbsp smooth peanut butter",
                "Stir in 3 tbsp Thai red curry paste, 1 tbsp smooth peanut butter and 500g sweet potato./Bring to the boil/Stir through 200g spinach and the juice of 1 lime, and season well.",
                "https://s3-eu-west-1.amazonaws.com/s3-gousto-production-media/cms/mood-image/2073_Joes-Satay-Sweet-Potato--Kale-Curry-0530-1588586628748.jpg", "https://images.immediate.co.uk/production/volatile/sites/30/2020/08/satay-sweet-potato-curry-17cc62d.jpg");
        Meal meal11 = new Meal("Vegan brownies", DietType.VEGAN, 7.5f, "200g dark chocolate/1 tsp coffee granules/125g self-raising flour/70g ground almonds/2 tsp baking powder/50g cocoa powder/250g golden caster sugar/1 and a half tsp vanilla extract",
                "Melt 120g chocolate, the coffee and margarine with 60ml water on a low heat./Put the flour, almonds, cocoa, baking powder and half a tsp salt in a bowl and stir to remove any lumps.", "https://veganinthefreezer.com/wp-content/uploads/2020/08/Vegan-Brownie-Recipe-square-feature-2-sp.jpg", "https://images.immediate.co.uk/production/volatile/sites/30/2020/08/brownies-ba587aa.jpg?quality=90&resize=440,400");
        Meal meal12 = new Meal("Vegan pizza Margherita", DietType.VEGAN, 9.0f, "1 tbsp coconut oil/2 cloves garlic/1 onions/3 tbsp Thai red curry paste/500g sweet potato/200g bag spinach/1 lime",
                "Stir in 3 tbsp Thai red curry paste, 1 tbsp smooth peanut butter and 500g sweet potato./Bring to the boil, turn down the heat and simmer, uncovered, for 25-30 mins or until the sweet potato is soft./Serve with cooked rice.",
                "https://healthiir.com/wp-content/uploads/2020/05/DSC8887-2-1024x1280.jpg", "https://images.immediate.co.uk/production/volatile/sites/30/2020/08/vegan-pizza-67125d6.jpg?quality=90&webp=true&resize=440,400");
        Meal meal13 = new Meal("Vegan banana cupcakes", DietType.VEGAN, 7.0f, "2 bananas/150g flour/50ml oil/2 tsp cinnamon/2 tbsp oats/2 tbsp mixed seeds/50g dried fruit and seed mix",
                "Mash the bananas./Add the flour, baking powder, cinnamon and dried fruit and seed mix./Mix until well combined./Line a cupcake tray with 10-12 cupcake cases and fill them full of the mixture./Bake for 25 mins.",
                "https://www.rhiansrecipes.com/wp-content/uploads/2019/01/IMG_7273-2.jpeg", "https://www.aimadeitforyou.com/wp-content/uploads/2016/04/vegan-banana-cupcakes-blog-8-500x500.jpg");
        Meal meal14 = new Meal("Veggie okonomiyaki", DietType.VEGAN, 8.9f, "3 eggs/50g flour/50ml milk/4 onions/200g Savoy cabbage/1 red chilli/1 heaped tbsp low-fat mayo/1 lime",
                "Whisk together the eggs, flour and milk until smooth./Add half the spring onions, the pak choi, cabbage, chilli and soy sauce/Add the flour, baking powder, cinnamon and dried fruit and seed mix.",
        "https://images.immediate.co.uk/production/volatile/sites/30/2020/08/veggie-okonomiyaki-de8fe85.jpg", "https://i2.wp.com/fullofplants.com/wp-content/uploads/2017/10/the-best-vegan-okonomiyaki-gluten-free-with-jackfruit-japanese-inspired-thumb.jpg?fit=1400%2C1400&ssl=1");
        Meal meal15 = new Meal("Gluten-free pancakes", DietType.VEGAN, 8.0f, "125g gluten-free plain flour/1 egg/250ml milk/1 butter",
                "Put the flour in a bowl and make a well in the centre./Crack the egg in the middle and pour in a quarter of the milk./Use an electric or balloon whisk to thoroughly combine the mixture.",
                "https://www.mamaknowsglutenfree.com/wp-content/uploads/2019/01/gluten-free-pancakes-rc.jpg", "https://www.biggerbolderbaking.com/wp-content/uploads/2020/04/How-to-Make-Gluten-Free-Pancakes-WS-thumbnail.jpg");
        Meal meal16 = new Meal("Nutty chicken satay strips", DietType.NORMAL, 9.2f, "1 garlic clove/2 tbsp chunky peanut butter/1 tsp Madras curry powder/2 tsp lime juice/2 skinless, chicken breast fillets",
                "Mix 2 tbsp chunky peanut butter with 1 finely grated garlic clove, 1 tsp Madras curry powder, a few shakes of soy sauce and 2 tsp lime juice in a bowl./Add 2 skinless chicken breast fillets, cut into strips, and mix well.",
                "https://iamafoodblog.b-cdn.net/wp-content/uploads/2019/06/satay-4546w-1024x683.jpg", "https://images.immediate.co.uk/production/volatile/sites/30/2020/08/nutty-chicken-sate-strips-67269e0.jpg?quality=90&webp=true&resize=440,400");

        Meal meal17 = new Meal("Mexican egg roll", DietType.NORMAL, 9.5f, "1 egg/2 tbsp tomato salsa/1 tbsp fresh coriander",
                "Beat the egg with 1 tbsp water./Heat the oil in a medium non-stick pan./Add the egg and swirl round the base of the pan, as though you are making a pancake, and cook until set.",
                "https://www.foodista.com/sites/default/files/californian-egg-roll-50212051%20%283%29.jpg", "https://www.yellowblissroad.com/wp-content/uploads/2020/01/southwest-air-fryer-egg-rolls-social.jpg");

        Meal meal18 = new Meal("Low-sugar lime & basil green juice", DietType.NORMAL, 9.8f, "70ml chilled apple and elderflower juice/50g baby spinach/20g basil leaves/6cm piece of cucumber/1 lime",
                "Pour the apple juice into a large jug then add the spinach, basil, cucumber, lime and 100ml chilled water./Blitz really well with a hand blender until very smooth./Pour into a glass and drink straightaway.",
                "https://images.immediate.co.uk/production/volatile/sites/30/2020/08/green-smoothie-388db40.jpg", "https://www.vibrantplate.com/wp-content/uploads/2018/01/Green-smoothie-02-480x360.jpg");


        Diet diet1 = new Diet("Dukan diet", "The Dukan diet is a high-protein, low-carb weight loss diet split into four phases./The weight loss phases are primarily based on eating unlimited high-protein foods and mandatory oat bran.", 60);
        Diet diet2 = new Diet("Vegan diet", "The vegan diet restricts all animal products for ethical, environmental, or health reasons.", 50);
        Diet diet3 = new Diet("Atkins diet", "The Atkins diet is the most well-known low-carb weight loss diet./Its proponents insist that you can lose weight by eating as much protein and fat as you like, as long as you avoid carbs.", 35);
        Diet diet4 = new Diet("Low-carb diet", "The primary aim of the diet is to force your body to use more fats for fuel instead of using carbs as a main source of energy.", 60);




        mealRepository.save(meal1);
        mealRepository.save(meal2);
        mealRepository.save(meal3);
        mealRepository.save(meal4);
        mealRepository.save(meal5);
        mealRepository.save(meal6);
        mealRepository.save(meal7);
        mealRepository.save(meal8);
        mealRepository.save(meal9);
        mealRepository.save(meal10);
        mealRepository.save(meal11);
        mealRepository.save(meal12);
        mealRepository.save(meal13);
        mealRepository.save(meal14);
        mealRepository.save(meal15);
        mealRepository.save(meal16);
        mealRepository.save(meal17);
        mealRepository.save(meal18);

        List<Meal> mealsList = new ArrayList<>();

        mealsList.add(meal7);
        mealsList.add(meal8);
        mealsList.add(meal9);

        diet1.setDietMeals(mealsList);
        dietRepository.save(diet1);

        // ===============================
        mealsList = new ArrayList<>();
        mealsList.add(meal1);
        mealsList.add(meal2);
        mealsList.add(meal10);
        mealsList.add(meal11);
        mealsList.add(meal12);

        diet2.setDietMeals(mealsList);
        dietRepository.save(diet2);

        // ===============================
        mealsList = new ArrayList<>();
        mealsList.add(meal13);
        mealsList.add(meal14);
        mealsList.add(meal15);

        diet3.setDietMeals(mealsList);
        dietRepository.save(diet3);

        // ===============================
        mealsList = new ArrayList<>();
        mealsList.add(meal16);
        mealsList.add(meal17);
        mealsList.add(meal18);

        diet4.setDietMeals(mealsList);
        dietRepository.save(diet4);

        // =============================================================================================================

        Diet diet = new Diet("Keto Diet", "Description of Keto Diet", 69);
        dietRepository.save(diet);

        List<Goal> goals = new ArrayList<>();
        Goal goal = new Goal("Be healthier", "/");
        goals.add(goal);

        goalRepository.save(goal);

        List<String> listGoals = new ArrayList<>();
        listGoals.add("Be healthier");

        User user = userService.register("test", "test", "test@mail.com", "John", "Smith", "2021-01-01", "/", 21, 172, 80, DietType.NORMAL, Role.ROLE_BASIC, listGoals).get();

        User nutritionist = new User("testN", "test", "testN@mail.com", "Alex",
                "James", "2021-02-03", "/", 25, 180.0F, 69.0F,
                DietType.NORMAL, Role.ROLE_NUTRITIONIST, goals);

        Post post = postService.publishPost("Healthy breakfast", "vegan", "Vegan brownies", "STARDUST WAY, MURRIETA, CA");


        user.setDiet(diet);
        user.setNutritionist(nutritionist);

        userRepository.save(nutritionist);
        userRepository.save(user);


        //SpringApplication.run(DiettasticApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

}
