class CreateComments < ActiveRecord::Migration
  def change
    create_table :comments do |t|
      t.string :commenter
      t.string :commentText
      t.references :todo, index: true, foreign_key: true

      t.timestamps null: false
    end
  end
end
