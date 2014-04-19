package org.multibit.hd.ui.fest.use_cases.sidebar_screens;

import org.fest.swing.fixture.FrameFixture;
import org.multibit.hd.ui.fest.use_cases.AbstractFestUseCase;
import org.multibit.hd.ui.languages.MessageKey;

import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;

/**
 * <p>Use case to provide the following to FEST testing:</p>
 * <ul>
 * <li>Verify the "exit" sidebar screen</li>
 * </ul>
 *
 * @since 0.0.1
 *  
 */
public class ExitScreenUseCase extends AbstractFestUseCase {

  public ExitScreenUseCase(FrameFixture window) {
    super(window);
  }

  @Override
  public void execute(Map<String, Object> parameters) {

    assertThat(parameters).isNotNull();

    window
      .tree(MessageKey.SIDEBAR_TREE.getKey())
      .requireVisible()
      .requireEnabled()
      .selectRow(8);

    // Expect the Exit wizard to show
    window
      .button(MessageKey.EXIT.getKey())
      .requireVisible()
      .requireEnabled();

    window
      .button(MessageKey.CANCEL.getKey())
      .requireVisible()
      .requireEnabled()
      .click();

  }

}
